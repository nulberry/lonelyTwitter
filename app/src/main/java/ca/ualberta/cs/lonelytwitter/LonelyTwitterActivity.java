package ca.ualberta.cs.lonelytwitter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class LonelyTwitterActivity extends Activity {

	private static final String FILENAME = "file.sav";
	private EditText bodyText;
	private ListView oldTweetsList;

	private ArrayList<Tweet> tweets = new ArrayList<Tweet>();
	private ArrayAdapter<Tweet> adapter;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		bodyText = (EditText) findViewById(R.id.body);
		Button saveButton = (Button) findViewById(R.id.save);
		Button clearButton = (Button) findViewById(R.id.clear);
		oldTweetsList = (ListView) findViewById(R.id.oldTweetsList);

		saveButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				/*Tweet tweet = new ImportantTweet("");
				NormalTweet tweet1 = new NormalTweet("");

				HappyMood mood1 = new HappyMood();
				SadMood mood2 = new SadMood();
				//Log.d("Debugging", "mood1: " + mood1.getMood());
				//Log.d("Debugging", "mood2: " + mood2.getMood());
				tweet.addMood(mood1);
				tweet.addMood(mood2);
				tweet1.addMood(mood2);
				//Log.d("Debugging", "tweet moods: " + tweet.getMoods());
				//Log.d("Debugging", "tweet1 moods: " + tweet1.getMoods());

				try {
					tweet.setMessage("Hello");
				} catch (TweetTooLongException e) {
					//e.printStackTrace();
				}

				ArrayList<Tweet> tweets = new ArrayList<Tweet>();
				tweets.add(tweet);
				tweets.add(tweet1);
				for (Tweet t : tweets){
					Log.d("Some Tag", "The isImportant method on this object returns " + t.isImportant());
					Log.d("Some Tag", "The getMoods method on this object returns " + t.getMoods());
				}

				ArrayList<Tweetable> tweetables = new ArrayList<Tweetable>();
				tweetables.add(tweet);
				tweetables.add(tweet1);*/

				setResult(RESULT_OK);
				String text = bodyText.getText().toString();

				tweets.add(new NormalTweet(text));
				adapter.notifyDataSetChanged();
				saveInFile();
				//finish();

			}
		});

		clearButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				deleteFile();
				adapter.notifyDataSetChanged();
			}
		});
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		loadFromFile();
		adapter = new ArrayAdapter<Tweet>(this, R.layout.list_item, tweets);
		oldTweetsList.setAdapter(adapter);
	}

	private void loadFromFile() {
		//ArrayList<String> tweets = new ArrayList<String>();
		try {
			FileInputStream fis = openFileInput(FILENAME);
			BufferedReader in = new BufferedReader(new InputStreamReader(fis));

			Gson gson = new Gson();
			Type listType = new TypeToken<ArrayList<NormalTweet>>() {}.getType();
			tweets = gson.fromJson(in, listType);
			/*
			String line = in.readLine();
			while (line != null) {
				tweets.add(line);
				line = in.readLine();*/
			}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			tweets = new ArrayList<Tweet>();
			//e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
			//e.printStackTrace();
		}
		//return tweets.toArray(new String[tweets.size()]);
		return;
	}
	
	private void saveInFile() {
		try {
			FileOutputStream fos = openFileOutput(FILENAME,
					Context.MODE_PRIVATE);

			BufferedWriter writer = new BufferedWriter( new OutputStreamWriter(fos));
			Gson gson = new Gson();
			Log.d("Debugging", "writing file");
			gson.toJson(tweets,writer);
			writer.flush();
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new RuntimeException();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new RuntimeException();
		}
	}

	private void deleteFile() {
		try {
//			BufferedWriter out = new BufferedWriter(new FileWriter(new File(FILENAME), true));
//			out.write("");
//			out.newLine();
//			out.close();
			FileOutputStream fos = openFileOutput(FILENAME,
					Context.MODE_PRIVATE);

			BufferedWriter writer = new BufferedWriter( new OutputStreamWriter(fos));
			Gson gson = new Gson();
			Log.d("Debugging", "clearing file");
			tweets.clear();
			gson.toJson(tweets,writer);
			writer.flush();
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			Log.d("Debugging", "FileNotFound");
			throw new RuntimeException();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			Log.d("Debugging", "IO");
			throw new RuntimeException();
		}
	}
}