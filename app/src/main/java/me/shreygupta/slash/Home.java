package me.shreygupta.slash;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class Home extends Activity {
    private Button button01;
    private Button button02;
    private EditText editText01;
    private EditText editText02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Parse.initialize(this, "DJ26KgKYG9pkqPo8VukDxR2lKWCI55Y1bHlHfDSL", "RwFiJj8gGifBzu3UHhjgGvY8HegCPxkCmOR5ZWby");
        button01 = (Button)findViewById(R.id.signupButton);
        button02 = (Button)findViewById(R.id.loginButton);
        editText01 = (EditText)findViewById(R.id.email);
        editText02 = (EditText)findViewById(R.id.password);
        button01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseUser user = new ParseUser();
                user.setUsername(editText01.getText().toString());
                user.setPassword(editText02.getText().toString());

                user.signUpInBackground(new SignUpCallback() {
                    public void done(com.parse.ParseException e) {
                        if (e == null) {
                            editText01.setText("");
                            editText02.setText("");
                        } else {
                            editText01.setText("User creation failed! ERROR: " + e);
                        }
                    }
                });
            }
        });
        button02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseUser user = new ParseUser();
                user.setUsername(editText01.getText().toString());
                user.setPassword(editText02.getText().toString());

                ParseUser.logInInBackground(editText01.getText().toString(), editText02.getText().toString(), new LogInCallback() {
                    public void done(ParseUser user, com.parse.ParseException e) {
                        if (e == null) {
                            editText01.setText("");
                            editText02.setText("");
                        } else {
                            editText01.setText("User login failed! ERROR: " + e);
                        }
                    }
                });
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
