import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-items-redeem',
  templateUrl: './items-redeem.component.html',
  styleUrls: ['./items-redeem.component.scss']
})
export class ItemsRedeemComponent implements OnInit {
  userForm: FormGroup;

  constructor(
    public dialogRef: MatDialogRef<ItemsRedeemComponent>,
    private builder: FormBuilder
  ) { }

  ngOnInit() {
    this.buildForm();
  }

  buildForm() {
    this.userForm = this.builder.group({
      firstName: ['', [
        Validators.required
      ]],
      lastName: ['', [
        Validators.required
      ]],
      email: ['', [
        Validators.required
      ]],
      mobileNumber: ['', [
        Validators.required
      ]],
      typeOfId: ['', [
        Validators.required
      ]],
      idNumber: ['', [
        Validators.required
      ]]
    });
  }

  dismiss() {
    this.dialogRef.close();
  }

  save() {
    if (this.userForm.valid) {
      this.dialogRef.close(this.userForm.value);
    }
  }
}
