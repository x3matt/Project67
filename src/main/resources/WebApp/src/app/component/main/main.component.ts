import { Component, OnInit } from '@angular/core';
import {PostService} from '../../service/post/post.service';
import {PageDto} from '../../dto/PageDto';


@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.scss']
})
export class MainComponent implements OnInit {
  posts: PageDto;
  constructor(private postService: PostService) { }


  ngOnInit() {
    this.postService.getAllPostsPages(0, 5).subscribe(
      posts => {
        this.posts = posts;
      }
    );
  }
  updatePage(newPage){
    this.postService.getAllPostsPages(newPage, 5).subscribe(
      posts => {
        this.posts = posts;
      }
    );
  }

}
