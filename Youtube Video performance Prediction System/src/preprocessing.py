import pandas as pd

def preprocess():
    df = pd.read_csv("data/data.csv")

    df.fillna(0, inplace=True)

    numeric_cols = ["views", "likes", "comments", "subscribers", "videos"]

    for col in numeric_cols:
        df[col] = pd.to_numeric(df[col], errors='coerce')

    df.dropna(inplace=True)

    df.to_csv("data.csv", index=False)
    print("Preprocessing done")

if __name__ == "__main__":
    preprocess()