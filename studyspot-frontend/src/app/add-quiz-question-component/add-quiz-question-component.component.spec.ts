import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddQuizQuestionComponentComponent } from './add-quiz-question-component.component';

describe('AddQuizQuestionComponentComponent', () => {
  let component: AddQuizQuestionComponentComponent;
  let fixture: ComponentFixture<AddQuizQuestionComponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddQuizQuestionComponentComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddQuizQuestionComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
