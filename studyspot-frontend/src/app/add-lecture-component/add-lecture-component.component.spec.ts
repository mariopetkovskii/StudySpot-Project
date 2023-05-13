import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddLectureComponentComponent } from './add-lecture-component.component';

describe('AddLectureComponentComponent', () => {
  let component: AddLectureComponentComponent;
  let fixture: ComponentFixture<AddLectureComponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddLectureComponentComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddLectureComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
