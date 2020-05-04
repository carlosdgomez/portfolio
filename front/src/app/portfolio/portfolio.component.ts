import { Component, OnInit, Input } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

import { Portfolio } from '../core/models/portfolio.model';
import { TwitterTimelineData } from '../core/models/twitter-timeline-data.model';
import { TwitterTimelineOpts } from '../core/models/twitter-timeline-options.model';
import { PortfolioService } from '../core/services/portfolio.service';
import { PortfolioRequest } from '../core/models/portfolio-request.model';

@Component({
  selector: 'app-portfolio',
  templateUrl: './portfolio.component.html',
  styleUrls: ['./portfolio.component.css']
})
export class PortfolioComponent implements OnInit {

  @Input() portfolio: Portfolio;
  editForm: FormGroup;
  isFormSubmitted: boolean;
  isRequesting: boolean;
  isReadonlyView: boolean;
  isNotEmptyPortfolio: boolean;
  twitterTimelineData: TwitterTimelineData;
  twitterTimelineOpts: TwitterTimelineOpts;
  showTweets: boolean;
  errorMessage: String;

  constructor(
      private formBuilder: FormBuilder,
      private portfolioService: PortfolioService) {
    this.createPortfolioForm();
    this.isReadonlyView = true;
    this.isNotEmptyPortfolio = true;
  }

  ngOnInit() {
    this.buildFormFromScratch();
  }

  get f() {
    return this.editForm.controls;
  }

  buildFormFromScratch() {
    this.updateTwitterTimeline();
    this.twitterTimelineOpts = new TwitterTimelineOpts(5, 'dark');

    this.patchForm();
  }

  createPortfolioForm() {
    const DEFAULT_VALUE = '';

    this.editForm = this.formBuilder.group({
      description: [DEFAULT_VALUE, Validators.required],
      imageUrl: [
        DEFAULT_VALUE,
        [
          Validators.required,
          Validators.pattern('^[(http(s)?):\/\/(www\.)?a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*)$')
        ]
      ],
      twitterUserName: [
        DEFAULT_VALUE,
        [
          Validators.required,
          Validators.pattern('[a-zA-Z0-9_]{1,15}')
        ]
      ],
      title: [DEFAULT_VALUE, Validators.required]
    });
  }

  patchForm() {
    const { description, imageUrl, twitterUserName, title } = this.portfolio;
    this.editForm.patchValue({
      description,
      imageUrl,
      twitterUserName,
      title
    });
    this.errorMessage = undefined;
  }

  toggleEditView() {
    this.errorMessage = '';
    this.isReadonlyView = !this.isReadonlyView;
  }

  onSubmit() {
    this.isFormSubmitted = true;

    if (this.editForm.invalid) {
      return;
    }

    const portfolioId = this.portfolio.id;
    const portfolioRequest = new PortfolioRequest(
      this.f.description.value,
      this.f.imageUrl.value,
      this.f.twitterUserName.value,
      this.f.title.value
    );

    this.isRequesting = true;
    this.portfolioService.updatePortfolio(portfolioId, portfolioRequest).subscribe(
      data => {
        this.portfolio.description = portfolioRequest.description;
        this.portfolio.imageUrl = portfolioRequest.imageUrl;
        this.portfolio.twitterUserName = portfolioRequest.twitterUserName;
        this.portfolio.title = portfolioRequest.title;

        this.updateTwitterTimeline();

        this.isReadonlyView = true;
        this.isRequesting = false;
      },
      error => {
        this.errorMessage = error.error.message;
        this.isRequesting = false;
      }
    );
  }

  updateTwitterTimeline() {
    const sourceType = 'url';
    const url = `https://twitter.com/${this.portfolio.twitterUserName}`;
    this.twitterTimelineData = new TwitterTimelineData(sourceType, url);
  }

  toggleTwitterTimeline() {
    this.showTweets = !this.showTweets;
  }

  hasNext(): boolean {
    return this.isNotEmptyPortfolio;
  }

  requestNext() {
    this.portfolioService.loadNext().subscribe(
      (portfolios: Portfolio[]) => {
        this.isNotEmptyPortfolio = portfolios[0] && true;
        this.portfolio = portfolios[0];
        this.buildFormFromScratch();
      }
    );
  }

  hasPrev(): boolean {
    return this.portfolioService.getPageIndex() > 0;
  }

  requestPrev() {
    this.portfolioService.loadPrev().subscribe(
      (portfolios: Portfolio[]) => {
        this.isNotEmptyPortfolio = portfolios[0] && true;
        this.portfolio = portfolios[0],
        this.buildFormFromScratch();
      }
    );
  }
}
