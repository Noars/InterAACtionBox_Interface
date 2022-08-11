cd C:\\Users\\%USERNAME%\\Documents\\InterAACtionBoxAFSR
rmdir /s /q AugCom
powershell Start-BitsTransfer -Source "https://github.com/InteraactionGroup/InterAACtionScene/releases/latest/download/AugCom.zip" -Destination "AugCom.zip"
powershell Add-Type -A 'System.IO.Compression.FileSystem';[IO.Compression.ZipFile]::ExtractToDirectory('AugCom.zip', '../InterAACtionBoxAFSR')
del AugCom.zip