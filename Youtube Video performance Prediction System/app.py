import streamlit as st
import pickle
import pandas as pd
import numpy as np

# Load models
reg_model = pickle.load(open("reg_model.pkl", "rb"))
clf_model = pickle.load(open("clf_model.pkl", "rb"))
columns = pickle.load(open("columns.pkl", "rb"))

st.title("YouTube Prediction System")

inputs = {}

# Input fields
for col in ["likes", "comments", "subscribers", "videos"]:
    inputs[col] = st.number_input(f"Enter {col}", min_value=0.0)

if st.button("Predict"):
    try:
        # Feature engineering
        data = inputs.copy()
        data["like_ratio"] = data["likes"] / (data["subscribers"] + 1)
        data["comment_ratio"] = data["comments"] / (data["subscribers"] + 1)
        data["engagement"] = (data["likes"] + data["comments"]) / (data["subscribers"] + 1)

        df = pd.DataFrame([data])[columns]

        # 🔹 Regression
        pred_log = reg_model.predict(df)[0]
        views_pred = np.expm1(pred_log)

        # 🔹 Classification
        viral_pred = clf_model.predict(df)[0]

        st.success(f"Predicted Views: {round(views_pred, 2)}")

        if viral_pred == 1:
            st.success("This video is likely to go VIRAL!")
        else:
            st.warning("This video may not go viral")

    except Exception as e:
        st.error(f"Error: {e}")