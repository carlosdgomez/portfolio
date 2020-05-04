import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgxTwitterTimelineModule } from 'ngx-twitter-timeline';

import { AppComponent } from './app.component';
import { PortfolioComponent } from './portfolio/portfolio.component';
import { ImagePreloadDirective } from './core/directives/image-preload.directive';

@NgModule({
  declarations: [
    AppComponent,
    PortfolioComponent,
    ImagePreloadDirective
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    NgxTwitterTimelineModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
