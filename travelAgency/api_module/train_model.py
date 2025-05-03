from sklearn.linear_model import LinearRegression
import numpy as np
import joblib

X = np.array([[1], [2], [3], [5]])
y = np.array([3, 5, 7, 11])  # y = 2x + 1

model = LinearRegression()
model.fit(X, y)

joblib.dump(model, "model.pkl")

