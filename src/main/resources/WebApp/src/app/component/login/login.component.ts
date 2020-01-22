import { Component, OnInit } from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {TranslateService} from "@ngx-translate/core";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  translations: any;

  constructor(private fb: FormBuilder,private translateService: TranslateService) { }

  ngOnInit() {
    this.loginForm = this.fb.group({
      login: ['',Validators.required],
      password: ['',Validators.required]
    });
    this.translateService.get('errors').subscribe(res => {
      this.translations = res;
    })
  }

  getControl(name): AbstractControl {
    return this.loginForm.controls[name];
  }

  getErrorTranslations(): any {
    return this.translations;
  }

  onSubmit($event: MouseEvent) {

  }
}
