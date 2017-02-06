import {Component, OnInit} from "@angular/core";
import {BooksService} from "./book.service";
import {Book} from "./book";

@Component({
    selector: 'book-list',
    template: `
<h2>All Books</h2>
<ul>
  <li *ngFor="let book of books">{{book.title}} from {{book.author}}</li>
</ul>
`
})
export class BookListComponent implements OnInit {

    books: Book[];
    errorMessage: any;

    constructor(private bookService: BooksService) {
    }

    ngOnInit(): void {
        this.books = [];
        this.bookService.getBooks()
            .subscribe(
                books => this.books = books,
                error => this.errorMessage = <any>error);
    }
}