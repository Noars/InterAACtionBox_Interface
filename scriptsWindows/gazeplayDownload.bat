cd C:\\Users\\%USERNAME%\\Documents\\InterAACtionBoxAFSR
rmdir /s /q GazePlay
powershell Start-BitsTransfer -Source "https://github.com/GazePlay/GazePlay/releases/latest/download/GazePlay.zip" -Destination "GazePlay.zip"
powershell Add-Type -A 'System.IO.Compression.FileSystem';[IO.Compression.ZipFile]::ExtractToDirectory('GazePlay.zip', '../InterAACtionBoxAFSR')
del GazePlay.zip