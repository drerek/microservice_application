import { Component, EventEmitter, Input, Output, OnInit } from '@angular/core';
import {ItemComment} from "./comment";

@Component({
  selector: 'comment-list',
  templateUrl: './comment-list.component.html',
  styleUrls: ['./comment-list.component.css']
})
export class CommentListComponent implements OnInit {

  @Input() comment: ItemComment;
  @Output() deleteComment = new EventEmitter<boolean>();

  ownerLogin: string;

  constructor() { }

  ngOnInit() {
    this.ownerLogin = JSON.parse(localStorage.getItem('currentUser')).login;
  }

  deleteClicked() {
    this.deleteComment.emit(true);
  }

}
