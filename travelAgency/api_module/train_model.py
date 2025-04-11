from sklearn.linear_model import LinearRegression
import numpy as np
import joblib

X = np.array([[1], [2], [3], [4]])
y = np.array([3, 5, 7, 9])  # y = 2x + 1

model = LinearRegression()
model.fit(X, y)

joblib.dump(model, "model.pkl")

