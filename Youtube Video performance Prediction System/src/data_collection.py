
from googleapiclient.discovery import build
import pandas as pd

API_KEY = "AIzaSyDGw62hFU_KB8wjsnF6_Ua832k7m2TqDIc"

youtube = build('youtube', 'v3', developerKey=API_KEY)

def get_video_data(query, max_results=50):
    search_response = youtube.search().list(
        q=query,
        part='snippet',
        maxResults=max_results,
        type='video'
    ).execute()

    video_ids = []
    channel_ids = []

    for item in search_response['items']:
        video_ids.append(item['id']['videoId'])
        channel_ids.append(item['snippet']['channelId'])

    # 🔹 Get video stats
    video_response = youtube.videos().list(
        part="statistics",
        id=",".join(video_ids)
    ).execute()

    # 🔹 Get channel stats
    channel_response = youtube.channels().list(
        part="statistics",
        id=",".join(set(channel_ids))
    ).execute()

    # Map channel stats
    channel_data = {}
    for ch in channel_response['items']:
        channel_data[ch['id']] = {
            "subscribers": int(ch['statistics'].get('subscriberCount', 0)),
            "videos": int(ch['statistics'].get('videoCount', 0))
        }

    data = []

    for i, video in enumerate(video_response['items']):
        stats = video['statistics']
        ch_id = search_response['items'][i]['snippet']['channelId']

        data.append({
            "views": int(stats.get("viewCount", 0)),
            "likes": int(stats.get("likeCount", 0)),
            "comments": int(stats.get("commentCount", 0)),
            "subscribers": channel_data[ch_id]["subscribers"],
            "videos": channel_data[ch_id]["videos"]
        })

    df = pd.DataFrame(data)
    df.to_csv("data.csv", index=False)
    print("Real YouTube data saved!")

if __name__ == "__main__":
    get_video_data("technology")