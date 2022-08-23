cd C:\\Users\\%USERNAME%\\Documents\\InterAACtionBoxAFSR
rmdir /s /q GazePlay
powershell [Net.ServicePointManager]::SecurityProtocol = [Net.SecurityProtocolType]::Tls12; wget https://github.com/GazePlay/GazePlay/releases/latest/download/GazePlay.zip -OutFile "GazePlay.zip"
powershell Add-Type -A 'System.IO.Compression.FileSystem'; [IO.Compression.ZipFile]::ExtractToDirectory('GazePlay.zip', '../InterAACtionBoxAFSR')
del GazePlay.zip