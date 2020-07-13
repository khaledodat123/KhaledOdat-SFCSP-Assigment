package com.khaledodat.assessment.utils;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;

import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.khaledodat.assessment.view.custom.CustomSnapHelper;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Locale;

/**
 * Created by danieldobalian on 5/9/17.
 */

public class Helpers {

    private static String Base64UrlDecode(String s) {
        byte[] data = Base64.decode(s, Base64.DEFAULT | Base64.URL_SAFE);
        String output = "";
        try {
            output = new String(data, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
            return output;
        }
    }


    public static String join (List list, String separator) {
        String listToString = "";

        if (list == null || list.isEmpty()) {
            return listToString;
        }

        for (Object element : list) {
            listToString += element.toString() + separator;
        }

        return listToString;
    }

    public static LinearSnapHelper getSnapHelper(RecyclerView rv){
        CustomSnapHelper mSnapHelper = new CustomSnapHelper();

        rv.setOnFlingListener(null);
        mSnapHelper.attachToRecyclerView(rv);

        return mSnapHelper;
    }

    public static void getSnapHelper1(RecyclerView rv){
        LinearSnapHelper snapHelper = new LinearSnapHelper() {
            @Override
            public int findTargetSnapPosition(RecyclerView.LayoutManager layoutManager, int velocityX, int velocityY) {
                View centerView = findSnapView(layoutManager);
                if (centerView == null)
                    return RecyclerView.NO_POSITION;

                int position = layoutManager.getPosition(centerView);
                int targetPosition = -1;
                if (layoutManager.canScrollHorizontally()) {
                    if (velocityX < 0) {
                        targetPosition = position - 1;
                    } else {
                        targetPosition = position + 1;
                    }
                }

                if (layoutManager.canScrollVertically()) {
                    if (velocityY < 0) {
                        targetPosition = position - 1;
                    } else {
                        targetPosition = position + 1;
                    }
                }

                final int firstItem = 0;
                final int lastItem = layoutManager.getItemCount() - 1;
                targetPosition = Math.min(lastItem, Math.max(targetPosition, firstItem));
                return targetPosition;
            }
        };
        snapHelper.attachToRecyclerView(rv);
    }

    public static void makeHorizontalScrollHighPriority(RecyclerView rv){

        final float[] lastX = {0.0f};
        final float[] lastY = {0.0f};

        rv.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                int action = e.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        lastX[0] = e.getRawX();
                        lastY[0] = e.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        float a = e.getRawX();
                        float b = e.getRawY();

                        if (b < lastY[0] - 110 || b > lastY[0] + 110){
                            rv.getParent().requestDisallowInterceptTouchEvent(false);
                        }else{
                            rv.getParent().requestDisallowInterceptTouchEvent(true);
                        }
                        break;
                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
    }
    public static void share(Context ctx, String subject, String body){
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = body;
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, subject);
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        ctx.startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }

    public static void openFacebookIntent(Context context, String facebookUrl) {

        Intent intent;

        try {
            context.getPackageManager().getPackageInfo("com.facebook.katana", 0);
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse(facebookUrl));
//            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/facebookID"));
        } catch (Exception e) {
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse(facebookUrl));
        }

        context.startActivity(intent);
    }

    public static void openTwiterIntent(Context context, String twitterUrl) {

        Intent intent;

        try {
            context.getPackageManager().getPackageInfo("com.twitter.android", 0);
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse(twitterUrl));
//            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?user_id=twitterID"));
        } catch (Exception e) {
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse(twitterUrl));
        }

        context.startActivity(intent);
    }

    public static void openInstagramIntent(Context context, String instagramUrl) {

        Uri uri = Uri.parse(instagramUrl);
//        Uri uri = Uri.parse("http://instagram.com/_u/instagramID");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);

        intent.setPackage("com.instagram.android");

        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            context.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse(instagramUrl)));
        }
    }

    public static void openYoutubeIntent(Context context, String youtubeChannelUrl) {
        Intent intent=null;
        try {
            intent =new Intent(Intent.ACTION_VIEW);
            intent.setPackage("com.google.android.youtube");
            intent.setData(Uri.parse(youtubeChannelUrl));
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(youtubeChannelUrl));
            context.startActivity(intent);
        }
    }

    public static String formatNumber(long count) {
        if (count < 1000) return "" + count;
        int exp = (int) (Math.log(count) / Math.log(1000));
        return String.format(Locale.ENGLISH, "%.1f %c", count / Math.pow(1000, exp),"KMGTPE".charAt(exp-1));
    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}
