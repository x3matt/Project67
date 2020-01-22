import {Component, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {AbstractControl, FormArray, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {PostService} from '../../service/post/post.service';
import {TranslateService} from "@ngx-translate/core";

@Component({
  selector: 'app-edit-post',
  templateUrl: './edit-post.component.html',
  styleUrls: ['./edit-post.component.scss']
})
export class EditPostComponent implements OnInit {

  postForm: FormGroup;
  translations: any;


  constructor(private fb: FormBuilder,
              private postService: PostService,
              private translateService: TranslateService) { }

  ngOnInit() {
    this.postForm = this.fb.group({
      id: [],
      title: ['',[Validators.required,Validators.maxLength(64),Validators.minLength(4)]],
      body: ['',[Validators.required,Validators.maxLength(4096)]],
      date: ['',Validators.required]
      }
    );
    this.translateService.get('errors').subscribe((res: any) => {
      this.translations = res;
    });
  }

  getControl(name): AbstractControl {
    return this.postForm.controls[name];
  }

  getErrorTranslations(): any {
    return this.translations;
  }

  onSubmit($event) {
    console.log(this.postForm.getRawValue());
    this.postService.savePost(this.postForm.getRawValue())
      .subscribe(
        result => console.log(result)
      );
  }
}
