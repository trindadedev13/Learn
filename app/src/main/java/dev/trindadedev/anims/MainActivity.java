package dev.trindadedev.anims;

import android.graphics.BitmapFactory;
import android.graphics.Point;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import dev.trindadedev.anims.databinding.ActivityMainBinding;
import dev.trindadedev.anims.ui.components.DragView;

public class MainActivity extends AppCompatActivity {
  private ActivityMainBinding binding;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivityMainBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());

    binding.dragView.setSize(200, 200);
    binding.dragView.setPosition(new Point(50, 50));
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    this.binding = null;
  }
}
