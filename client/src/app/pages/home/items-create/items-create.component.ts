import { ItemModel } from './../../../models/item';
import { Category } from './../../../models/category';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ItemService } from 'src/app/services/item.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-items-create',
  templateUrl: './items-create.component.html',
  styleUrls: ['./items-create.component.scss']
})
export class ItemsCreateComponent implements OnInit {
  itemForm: FormGroup;
  CATEGORY = Category;
  CATEGORY_KEYS = Object.keys(Category);

  constructor(
    private builder: FormBuilder,
    private router: Router,
    private itemService: ItemService,
    private snackbar: MatSnackBar
  ) { }

  ngOnInit() {
    this.buildForm();
  }

  buildForm() {
    this.itemForm = this.builder.group({
      category: ['', [
        Validators.required
      ]],
      type: ['', [
        Validators.required
      ]],
      brand: ['', [
        Validators.required
      ]],
      description: ['', [
        Validators.required
      ]]
    });
  }

  addItem() {
    if (this.itemForm.valid) {
      this.itemService.create(this.itemForm.value as ItemModel).then((res: any) => {
        this.snackbar.open('Successfully added an item!', 'Close', {
          duration: 2000,
          verticalPosition: 'top'
        });
      });
    }
  }

  goTo(path) {
    this.router.navigate([path]);
  }

  get category() {
    return this.itemForm.get('category');
  }

  get type() {
    return this.itemForm.get('type');
  }

  get brand() {
    return this.itemForm.get('brand');
  }

  get description() {
    return this.itemForm.get('description');
  }
}
