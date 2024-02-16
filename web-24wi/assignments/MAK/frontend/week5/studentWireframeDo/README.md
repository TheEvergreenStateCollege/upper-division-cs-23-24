user
tweets/replies
trending topics
DMs


```mermaid
erDiagram
  USER || --o{ TWEET : allows
  USER {
    string userName
    string location
    string dateTime
    }
  TOPICS ||--o { TWEET : is 
  TOPICS {
    int tweets
    int retweets
    int replies
    }
```


    u[Person] {
      string firstName
      string lastName
      }
    u["Account"] {
      string email
      }
    u ||--o| u : has
    

USER | ACCOUNT |
| ----- | ----- |
| @name | email@com |


| TRENDING | TWEETS |
| ---------- | ------ | 
| Nathan MacKinnon | 29 million |
| Cale Makar | 8 million |
| LandyDaddy | 92 million


    
