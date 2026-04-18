import pandas as pd
import numpy as np
import pickle
from sklearn.model_selection import train_test_split
from sklearn.metrics import r2_score, accuracy_score
from sklearn.ensemble import RandomForestRegressor, RandomForestClassifier

def train():
    df = pd.read_csv("data.csv")

    # Feature Engineering
    df["like_ratio"] = df["likes"] / (df["subscribers"] + 1)
    df["comment_ratio"] = df["comments"] / (df["subscribers"] + 1)
    df["engagement"] = (df["likes"] + df["comments"]) / (df["subscribers"] + 1)

    features = [
        "likes", "comments", "subscribers", "videos",
        "like_ratio", "comment_ratio", "engagement"
    ]

    X = df[features]

    # ======================
    # 🔹 REGRESSION MODEL
    # ======================
    y_reg = np.log1p(df["views"])  # log transform

    X_train, X_test, y_train, y_test = train_test_split(
        X, y_reg, test_size=0.2, random_state=42
    )

    reg_model = RandomForestRegressor(n_estimators=200, max_depth=10)
    reg_model.fit(X_train, y_train)

    preds = reg_model.predict(X_test)
    print("Regression R2 Score:", r2_score(y_test, preds))

    # ======================
    # 🔹 CLASSIFICATION MODEL
    # ======================

    # Define viral threshold (you can adjust)
    threshold = df["views"].median()

    df["viral"] = (df["views"] > threshold).astype(int)

    y_clf = df["viral"]

    X_train, X_test, y_train, y_test = train_test_split(
        X, y_clf, test_size=0.2, random_state=42
    )

    clf_model = RandomForestClassifier(n_estimators=200, max_depth=10)
    clf_model.fit(X_train, y_train)

    preds = clf_model.predict(X_test)
    print("Classification Accuracy:", accuracy_score(y_test, preds))

    # Save models
    pickle.dump(reg_model, open("reg_model.pkl", "wb"))
    pickle.dump(clf_model, open("clf_model.pkl", "wb"))
    pickle.dump(features, open("columns.pkl", "wb"))

    print("Both models saved!")

if __name__ == "__main__":
    train()