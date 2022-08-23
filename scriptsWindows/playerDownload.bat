cd C:\\Users\\%USERNAME%\\Documents\\InterAACtionBoxAFSR
rmdir /s /q InterAACtionPlayer
powershell [Net.ServicePointManager]::SecurityProtocol = [Net.SecurityProtocolType]::Tls12; wget https://github.com/InteraactionGroup/InterAACtionPlayer/releases/latest/download/InterAACtionPlayer.zip -OutFile "InterAACtionPlayer.zip"
powershell Add-Type -A 'System.IO.Compression.FileSystem'; [IO.Compression.ZipFile]::ExtractToDirectory('InterAACtionPlayer.zip', '../InterAACtionBoxAFSR')
del InterAACtionPlayer.zip