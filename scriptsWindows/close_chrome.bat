cd C:\\Users\\%USERNAME%\\Downloads
IF EXIST close161918.txt (
	del close161918.txt
	taskkill /IM chrome.exe /F
)