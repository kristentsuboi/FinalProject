import { CommentService } from './../../services/comment.service';
import { Component } from '@angular/core';
import { Comment } from './../../models/comment';

@Component({
  selector: 'app-forum',
  templateUrl: './forum.component.html',
  styleUrls: ['./forum.component.css'],
})
export class ForumComponent {
  comments: Comment[] = [];
  selected: Comment | null = null;
  selectedType: string = 'all';
  newComment: Comment = new Comment();

  constructor(private commentService: CommentService) {}

  ngOnInit(): void {
    this.loadComments();
  }

  topic = [
    'Health and Wellness',
    'Training',
    'Adoption and Rescue',
    'Pet Loss Support',
    'Traveling with Pets',
    'Pet Stories',
    'Pet Meme',
    'Pet Photography',
    'Grooming',
    'Kids and Pets',
  ];

  loadComments(): void {
    this.commentService.index().subscribe({
      next: (commentList) => {
        this.comments = commentList;
      },
      error: (error) => {
        console.error(
          'ForumComponent.loadComments(): Error loading comments list'
        );
        console.error(error);
      },
    });
  }

  validateForm(): void {
    if (this.newComment.body.length < 5) {
      alert('Please enter a question with at least 5 character.');
    } else {
      this.addNewComment(this.newComment);
    }
  }

  reload() {
    this.commentService.index().subscribe({
      next: (todoList) => {
        this.comments = todoList;
      },
      error: (someError) => {
        console.error('TodoListComponent.reload(): error getting todo list');
        console.error(someError);
      },
    });
  }

  addNewComment(newComment: Comment): void {
    console.log(newComment);
    this.commentService.create(newComment).subscribe({
      next: (createdComment) => {
        this.newComment = new Comment();
        this.reload();
      },
      error: (error) => {
        console.error('ForumComponent.addComment(): Error creating comment');
        console.error(error);
      },
    });
  }

  addComment(newComment: Comment): void {
    console.log(newComment);
    newComment.mainComment = this.selected
    this.commentService.create(newComment).subscribe({
      next: (createdComment) => {
        this.newComment = new Comment();
        this.selected?.replies.push(createdComment)
        this.reload();
      },
      error: (error) => {
        console.error('ForumComponent.addComment(): Error creating comment');
        console.error(error);
      },
    });
  }
}
