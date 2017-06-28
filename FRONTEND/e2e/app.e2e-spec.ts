import { DicegamePage } from './app.po';

describe('dicegame App', () => {
  let page: DicegamePage;

  beforeEach(() => {
    page = new DicegamePage();
  });

  it('should display welcome message', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('Welcome to app!!');
  });
});
