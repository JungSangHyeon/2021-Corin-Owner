package com.example.corincoronacheckincustomer.corinDomain.view.editMyInfoActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.corincoronacheckincustomer.R;
import com.example.corincoronacheckincustomer.corinDomain.Constant;
import com.google.android.material.chip.ChipGroup;

public class EditMyInfoActivity extends AppCompatActivity {

    // Associate
        // VIew
        private EditText nameEditText, ageEditText;
        private ChipGroup genderChipGroup;
        private Button editMyInfoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_my_info);

        // Associate
        View settingPrivateInfoInclude = this.findViewById(R.id.editMyInfoActivity_include_settingPrivateInfo);
        this.nameEditText = settingPrivateInfoInclude.findViewById(R.id.settingPrivateInfoLayout_nameEditText);
        this.ageEditText = settingPrivateInfoInclude.findViewById(R.id.settingPrivateInfoLayout_ageEditText);
        this.genderChipGroup = settingPrivateInfoInclude.findViewById(R.id.settingPrivateInfoLayout_genderGroup);
        this.editMyInfoButton = this.findViewById(R.id.editMyInfoActivity_editMyInfoButton);

        // Initialize
        Intent intent = this.getIntent();
        this.nameEditText.setHint(intent.getStringExtra(Constant.MyInfoTag.eName.name()));
        this.ageEditText.setHint(Integer.toString(intent.getIntExtra(Constant.MyInfoTag.eAge.name(), -1)));
        this.genderChipGroup.check(Constant.GenderChipMap.get(Constant.Gender.values()[intent.getIntExtra(Constant.MyInfoTag.eGender.name(), -1)]));
        this.editMyInfoButton.setOnClickListener(v->this.editMyInfo());
    }

    private void editMyInfo() {
        String name = this.nameEditText.getText().toString();
        String age = this.ageEditText.getText().toString();
        if(!name.equals("") && !age.equals("")){
            Intent intent = new Intent();
            intent.putExtra(Constant.MyInfoTag.eName.name(), name);
            intent.putExtra(Constant.MyInfoTag.eAge.name(), Integer.parseInt(age));
            intent.putExtra(Constant.MyInfoTag.eGender.name(), this.genderChipGroup.getCheckedChipId());
            this.setResult(Constant.EditMyInfoActivityResultCode, intent);
            this.finish();
        }else{
            Toast.makeText(this, R.string.editMyInfoActivity_fillAllInfoText, Toast.LENGTH_SHORT).show();
        }
    }
}