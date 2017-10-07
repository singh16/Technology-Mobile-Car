package developer.tech_m_car;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class Feedback extends ActionBarActivity {

    EditText e;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        e=(EditText)findViewById(R.id.edittext);
    }

    public void send_feedback(View v)
    {
        Intent Email = new Intent(Intent.ACTION_SEND);   //Calling the intent
        Email.setType("text/email");                     //setting the type of intent
        Email.putExtra(Intent.EXTRA_EMAIL, new String[] { "jaspreetkaurlotay@gmail.com" });
        Email.putExtra(Intent.EXTRA_SUBJECT, "Feedback");
        Email.putExtra(Intent.EXTRA_TEXT, e.getText().toString());
        startActivity(Intent.createChooser(Email, "Send Feedback:")); //Heading given to the chooser
    }
}
