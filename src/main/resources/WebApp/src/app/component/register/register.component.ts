import { Component, OnInit } from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {TranslateService} from "@ngx-translate/core";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  registerForm: FormGroup;
  translations: any;

  constructor(private fb: FormBuilder, private translateService: TranslateService) { }

  ngOnInit() {
    this.registerForm = this.fb.group({
      login: ['',[Validators.required, Validators.minLength(2),Validators.maxLength(32)]],
      password: ['',[Validators.required,Validators.minLength(4), Validators.maxLength(64)]],
      mail: ['',[Validators.required, Validators.maxLength(64)]],
      biography: ['',Validators.maxLength(4096)]
    });
    this.translateService.get('errors').subscribe((res: any) => {
      this.translations = res;
    });
  }

  getControl(name): AbstractControl {
    return this.registerForm.controls[name];
  }

  getErrorTranslations(): any {
    return this.translations;
  }

  onSubmit($event: MouseEvent) {

  }
}
