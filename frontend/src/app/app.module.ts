import {HttpModule} from "@angular/http";
import {NgModule} from "@angular/core";
import {BrowserModule} from "@angular/platform-browser";
import {AppComponent} from "./app.component";
import {BookListComponent} from "./book/booklist.component";
import {BooksService} from "./book/book.service";

@NgModule({
    imports: [BrowserModule, HttpModule],
    declarations: [AppComponent, BookListComponent],
    bootstrap: [AppComponent],
    providers: [BooksService]
})
export class AppModule {
}
