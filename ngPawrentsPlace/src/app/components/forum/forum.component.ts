import { User } from 'src/app/models/user';
import { CommentService } from './../../services/comment.service';
import { Component } from '@angular/core';
import { Comment } from './../../models/comment';
import { AuthService } from 'src/app/services/auth.service';

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

  constructor(
    private commentService: CommentService,
    private authService: AuthService
  ) {}

  user: User = new User();


  ngOnInit(): void {
    this.loadComments();
    this.authService.getLoggedInUser().subscribe({
      next: (user) => {
        this.user = user;
      },
    });

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
      next: (comment) => {
        this.comments = comment;
      },
      error: (someError) => {
        console.error('TodoListComponent.reload(): error getting todo list');
        console.error(someError);
      },
    });
  }

  hardRefresh() {
    location.reload();
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

  replyToComment(newComment: Comment): void {
    console.log(newComment);
    newComment.mainComment = this.selected;
    this.commentService.create(newComment).subscribe({
      next: (createdComment) => {
        this.newComment = new Comment();
        this.selected?.replies.push(createdComment);
        this.reload();
      },
      error: (error) => {
        console.error('ForumComponent.addComment(): Error creating comment');
        console.error(error);
      },
    });
  }

  deleteComment(commentId: number) {
    this.commentService.destroy(commentId).subscribe({
      next: () => {
        this.hardRefresh();
      },
      error: (fail) => {
        console.error('CommentListComponent.deleteTodo(): error deleting');
        console.error(fail);
      },
    });
  }
}
