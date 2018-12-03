package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObjects;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectsFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class ArticleTests extends CoreTestCase
{
    private final static
    String search_query = "Mazda";
    String title_article_1 = "Ford Motor Company";
    String title_article_2 = "Ford F-Series";
    String Folder = "Bla-Bla";

    @Test
    public void testSaveTwoArticlesToMyList() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        MyListsPageObjects MyListsPageObjects = MyListsPageObjectsFactory.get(driver);
        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_query);
        SearchPageObject.clickByArticleWithSubstring(title_article_1);

        if(Platform.getInstance().isAndroid()){
            ArticlePageObject.addArticleInMyList(Folder);
        } else {
           ArticlePageObject.addArticleInMyiOSList();
           ArticlePageObject.closePlacesAuthClose();
        }

        ArticlePageObject.closeArticle();
        SearchPageObject.initSearchInput();

        if(Platform.getInstance().isAndroid()) {
            SearchPageObject.clickHistResultSearchSubstring(search_query);
            SearchPageObject.clickByArticleWithSubstring(title_article_2);
            ArticlePageObject.clickButtonAddArticleInMyList();
            MyListsPageObjects.clickByFolderWithSubstring(Folder);
        } else {
            SearchPageObject.clickByArticleWithSubstring(title_article_2);
            ArticlePageObject.addArticleInMyiOSList();
            ArticlePageObject.closePlacesAuthClose();
        }
        ArticlePageObject.closeArticle();
        NavigationUI.clickButtonMyLists();

        if(Platform.getInstance().isAndroid()) {
            MyListsPageObjects.clickByFolderWithSubstring(Folder);
            MyListsPageObjects.swipeByArticleToDelete(title_article_1);
            SearchPageObject.clickByArticleWithSubstring(title_article_2);
            String title_articlebox_2 = ArticlePageObject.getArticleTitle();
            assertTrue("Заголовок статьи номер 2 не совпадает с изначальным", title_articlebox_2.contains(title_article_2));
        } else {
            MyListsPageObjects.swipeByArticleToDelete(title_article_1);
            ArticlePageObject.existingArticleByLebel(title_article_2);
        }

    }




    @Test
    public void testSearchTitleInArticle() {

        SearchPageObject SearchPageObject =  SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Ford");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        SearchPageObject.clickByArticleWithSubstring("Ford Motor Company");
        String get_title_for_check =  ArticlePageObject.getArticleTitle();
        ArticlePageObject.existTitleInArticle();

    }
}
