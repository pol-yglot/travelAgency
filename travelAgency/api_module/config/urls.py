from django.contrib import admin
from django.urls import path, include

urlpatterns = [
    path('admin/', admin.site.urls),
    path('api/', include('predictor.urls')),  # /api/predict/ 경로 활성화
]
