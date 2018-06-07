package imagefetchapp.imagefetchapp;

/**
 * Created by shivam on 2/6/18.
 */
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

public class ImageAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<String> imagePath;
    private LayoutInflater layoutInflater;
    private Cursor cursor;

    public ImageAdapter(Context localContext,ArrayList<String> imagePath) {
        context = localContext;
        this.imagePath = imagePath;
        layoutInflater = LayoutInflater.from(context);
    }

    public ImageAdapter(MainActivity localContext, Cursor cursor) {
        this.context = localContext;
        this.cursor = cursor;
    }

    public int getCount() {
        return imagePath.size();
//        return cursor.getCount();
    }

    public Object getItem(int position) {
        return position;
    }
    public long getItemId(int position) {
        return position;
    }
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if (convertView == null) {

            convertView = layoutInflater.inflate(R.layout.listview_item,null);
            viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView)convertView.findViewById(R.id.image);
            viewHolder.txtFileName = (TextView)convertView.findViewById(R.id.txtFileName);

            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        String path = getThumbnailPath(context, imagePath.get(position));
        if (!TextUtils.isEmpty(path)){
            Uri uri = Uri.fromFile(new File(path));
            Picasso.get().load(uri).resize(500, 0).into(viewHolder.imageView);

            String filename=path.substring(path.lastIndexOf('/')+1);
            if (!TextUtils.isEmpty(filename)){
                viewHolder.txtFileName.setText(filename);
            }
        }



        return convertView;
    }

    public static String getThumbnailPath(Context context, String path)
    {
        long imageId = -1;

        String[] projection = new String[] { MediaStore.MediaColumns._ID };
        String selection = MediaStore.MediaColumns.DATA + "=?";
        String[] selectionArgs = new String[] { path };
        Cursor cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                projection, selection, selectionArgs, null);
        if (cursor != null && cursor.moveToFirst())
        {
            imageId = cursor.getInt(cursor.getColumnIndex(MediaStore.MediaColumns._ID));
            cursor.close();
        }

        String result = null;
        cursor = MediaStore.Images.Thumbnails.queryMiniThumbnail(context.getContentResolver(), imageId,
                MediaStore.Images.Thumbnails.MINI_KIND, null);
        if (cursor != null && cursor.getCount() > 0)
        {
            cursor.moveToFirst();
            result = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Thumbnails.DATA));
            cursor.close();
        }

        return result;
    }

    private static class ViewHolder {
        ImageView imageView;
        TextView txtFileName;
    }
}