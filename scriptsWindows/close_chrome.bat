@ECHO OFF
cd C:\\Users\\%USERNAME%\\Downloads
:_while
IF NOT EXIST close161918.txt (
	TIMEOUT /T 5 /NOBREAK > nul
	GOTO _while
) ELSE (
	del close161918.txt
	taskkill /IM chrome.exe /F
	GOTO _while
)