package fpt.edu.bottomsheetdialog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

import fpt.edu.bottomsheetdialog.model.Order;
import fpt.edu.bottomsheetdialog.model.Product;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnShow = findViewById(R.id.btn_show);
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickOpenBottomSheetDialogFragment();
                //clickOpenBottomSheet();
            }
        });
    }

    private void clickOpenBottomSheetDialogFragment() {
        List<Product> listProducts = new ArrayList<>();
        listProducts.add(new Product("Bun bo Hue"));
        listProducts.add(new Product("Pho Ha Noi"));
        listProducts.add(new Product("My Quang"));

        Order order = new Order("500 VND", listProducts, "61 Le Minh, An Dong, Hue");
        MyBottomSheetDialogFragment myBottomSheetDialogFragment = MyBottomSheetDialogFragment.newInstance(order);
        myBottomSheetDialogFragment.show(getSupportFragmentManager(), myBottomSheetDialogFragment.getTag());
    }

    private void clickOpenBottomSheet() {
        View viewDialog = getLayoutInflater().inflate(R.layout.layout_bottom_sheet, null);
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(viewDialog);
        bottomSheetDialog.show();
        bottomSheetDialog.setCancelable(false);

        Button btnCancel = viewDialog.findViewById(R.id.btn_cancel);
        Button btnPayment = viewDialog.findViewById(R.id.btn_payment);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.dismiss();
            }
        });

        btnPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Click Payment", Toast.LENGTH_SHORT).show();
            }
        });
    }
}