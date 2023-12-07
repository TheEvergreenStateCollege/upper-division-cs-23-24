# under development

from pydrive.auth import GoogleAuth
from pydrive.drive import GoogleDrive

# Authenticate with Google Drive (OneDrive)
gauth = GoogleAuth()
gauth.LocalWebserverAuth()  # This creates a local webserver and automatically handles authentication.

# Create GoogleDrive instance
gauth.SaveCredentialsFile("credentials.json")
drive = GoogleDrive(gauth)

# # Download a file by its file ID
# file_id = 'your_file_id_here'
# file_obj = drive.CreateFile({'id': file_id})
# file_obj.GetContentFile('downloaded_file.txt')  # Save the file with a desired name

# print(f"File '{file_obj['title']}' downloaded successfully.")
