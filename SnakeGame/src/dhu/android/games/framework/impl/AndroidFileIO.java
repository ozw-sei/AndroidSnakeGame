package dhu.android.games.framework.impl;

import android.content.res.AssetManager;
import android.os.Environment;
import dhu.android.games.framework.FileIO;

import java.io.*;

public class AndroidFileIO implements FileIO {
    AssetManager assets;
    String externalStoragePath;

    public AndroidFileIO(AssetManager assets) {
        this.assets = assets;
        this.externalStoragePath = Environment.getExternalStorageDirectory()
                .getAbsolutePath() + File.separator;
    }

    //アセットをリード
    @Override
    public InputStream readAsset(String fileName) throws IOException {
        return assets.open(fileName);
    }

    //ファイルのリード
    @Override
    public InputStream readFile(String fileName) throws IOException {
        return new FileInputStream(externalStoragePath + fileName);
    }

    //ファイル書き出し
    @Override
    public OutputStream writeFile(String fileName) throws IOException {
        return new FileOutputStream(externalStoragePath + fileName);
    }
}
