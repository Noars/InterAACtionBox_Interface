cd C:\\Users\\%USERNAME%\\Documents\\InterAACtionBoxAFSR
powershell Start-BitsTransfer -Source "https://github.com/InteraactionGroup/InterAACtionScene/releases/latest/download/InterAACtionScene.zip" -Destination "InterAACtionScene.zip"
powershell Add-Type -A 'System.IO.Compression.FileSystem';[IO.Compression.ZipFile]::ExtractToDirectory('InterAACtionScene.zip', '../InterAACtionBoxAFSR')
del InterAACtionScene.zip