from django.http import JsonResponse
from django.views.decorators.csrf import csrf_exempt
import joblib
import json
import numpy as np
import os

# 프로젝트 루트 기준으로 model.pkl 경로 설정
BASE_DIR = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
model_path = os.path.join(BASE_DIR, 'model.pkl')

# 모델 로드 (최초 1회)
model = joblib.load(model_path)

@csrf_exempt  # CSRF 보안 해제 (테스트용)
def predict(request):
    if request.method == 'POST':
        try:
            data = json.loads(request.body)
            x = float(data.get("x", 0))
            prediction = model.predict(np.array([[x]]))[0]
            return JsonResponse({"prediction": prediction})
        except Exception as e:
            return JsonResponse({"error": str(e)}, status=400)
    return JsonResponse({"error": "POST만 허용됩니다."}, status=405)
