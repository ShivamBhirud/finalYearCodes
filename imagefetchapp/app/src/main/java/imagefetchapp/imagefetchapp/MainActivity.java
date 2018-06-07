package imagefetchapp.imagefetchapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> imagePath = new ArrayList<>();
    private AlertDialog alertDialog;
    private String externalStorageDirectoryPath = Environment.getExternalStorageDirectory().getAbsolutePath()
            + "/DCIM/Camera";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getImageList();
    }

    //get all the images list from sd-card
    public void getImageList() {

        File dir =new File(externalStorageDirectoryPath);
        File file[] = dir.listFiles();

        for (int i = 0; i < file.length; ++i) {
            File files = file[i];
            if (files.isDirectory()) {
                readDirectory(files);

            } else {
                if (new MyImageFilter().accept(files, files.getAbsolutePath())){
                    imagePath.add(files.getAbsolutePath());
                }
            }
        }

        Log.d(getClass().getSimpleName(), "Images size :- " + imagePath.size());
        if (imagePath.size() > 0){
            GridView sdcardImages = (GridView) findViewById(R.id.gridView);

            sdcardImages.setAdapter(new ImageAdapter(this,imagePath));

            sdcardImages.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    showDialogForOptions(imagePath.get(position));
                }
            });
        }
    }

    /**
     * This function is used to show dialog with option to
     * delete file or Rename file.
     *
     * @param fileName
     */
    private void showDialogForOptions(final String fileName) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select your option");
        CharSequence charSequence []= {"Delete File","Rename File"};
        builder.setItems(charSequence, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                alertDialog.dismiss();
                if (which == 0) {
                    File file = new File(fileName);
                    if (file.isFile() && file.exists()) {
                        boolean isDeleted = file.delete();
                        Log.d(getClass().getSimpleName(), "File deleted :-" + isDeleted);
                        if (isDeleted) {
                            getImageList();
                        }
                    }
                } else {
                    alertDialog.dismiss();
                    showDialogToUpdateFileName(fileName);
                }
            }
        });

        alertDialog = builder.create();
        alertDialog.show();
    }

    /**
     * This function is used to update file name.
     * @param fileName
     */
    private void showDialogToUpdateFileName(final String fileName) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View view = layoutInflater.inflate(R.layout.update_file_name,null);
        builder.setView(view);

        final EditText etUpdatedName = (EditText)view.findViewById(R.id.etUpdatedFileName);
        Button btnUpdateName = (Button)view.findViewById(R.id.btnChangeName);
        Button btnCancel = (Button)view.findViewById(R.id.btnCancel);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        btnUpdateName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(etUpdatedName.getText().toString())){

                    alertDialog.dismiss();
                    String updatedFileName = etUpdatedName.getText().toString().trim();
                    if (updatedFileName .contains(".")){
                        String split[] = updatedFileName .split(".");
                        updatedFileName = split[0];
                    }

                    File file = new File(fileName);
                    if (file.isFile() && file.exists()){
                        File currentFileName = new File(fileName);
                        File directory = new File(externalStorageDirectoryPath);
                        File updatedFileNameIs = new File(directory,updatedFileName + ".jpeg");

                        boolean isFileNameUpdated = currentFileName.renameTo(updatedFileNameIs);
                        if (isFileNameUpdated){
                            getImageList();
                            Toast.makeText(MainActivity.this,"Your file name has been updated.",Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(MainActivity.this,"We are having some trouble in updating file name.",
                                    Toast.LENGTH_LONG).show();
                        }
                    }

                } else {
                    Toast.makeText(MainActivity.this,"Please enter file name to update file.",Toast.LENGTH_LONG).show();
                }
            }
        });

        alertDialog = builder.create();
        alertDialog.show();
    }

    public void readDirectory(File file){

        File files[] = file.listFiles();

        for (int i = 0 ; i < files.length ; i++){

            if (files[i].isDirectory()){
                readDirectory(files[i]);
            } else {

                if (new MyImageFilter().accept(file, files[i].getAbsolutePath())){
                    imagePath.add(files[i].getAbsolutePath());
                }
            }
        }
    }

}