# GitLabIssueBoardForAndroid
An Android app that replicates the functionality of GitLab's Issue board, built with Koin, MVVM, Retrofit, and more.

## Description
Eng: Create an Android app that mimics the GitLab Issue board.
Ch: 建立一個模仿GitLab Issue board的Android應用程式。

## How to implement

### 使用Koin, MVVM, ViewModel, Retrofit, Room, GitLab API, RecyclerView, ListAdapter, Flow, CardView, ConstraintLayout...
Steps:
- 1. Fetch the first 50 issues from the Android group on GitLab and store them in the app's local database using Room.
- 2. The app interface should display three boards: "Open", "Closed", and "All". Use ViewModel to categorize issues into these boards based on their status.
- 3. Users can filter issues by entering text in the search bar. The app should display issues
     that contain the entered text in their title.
- 4. Utilize Flow for data streaming.

- 1. 進入app或點擊refresh button撈取gitlab上 android group下前50筆issues，並用Room存在app db中
- 2. app畫面顯示open closed all三個面板，在viewModel中從db撈出來的所有issues中分類open/closed/all
- 2. 用戶在search bar輸入文字以過濾出含有輸入title的issue
- 3. 資料流使用Flow

### GitLab API
- https://docs.gitlab.com/ee/api/issues.html#list-issues
