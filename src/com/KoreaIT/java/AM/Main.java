package com.KoreaIT.java.AM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("==프로그램 시작==");

		Scanner sc = new Scanner(System.in);

		int lastArticleId = 0;

		List<Article> articles = new ArrayList<>();

		while (true) {
			System.out.printf("명령어) ");
			String cmd = sc.nextLine().trim();

			if (cmd.equals("exit")) {
				break;
			}
			if (cmd.length() == 0) {
				System.out.println("명령어를 입력해주세요");
				continue;
			}
			if (cmd.equals("article write")) {
				int id = lastArticleId + 1;
				lastArticleId = id;
				String regDate = Util.getDateStr();
				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();

				Article article = new Article(id, regDate, title, body);
				articles.add(article);

				System.out.printf("%d번 글이 생성되었습니다.\n", id);

			} else if (cmd.equals("article list")) {
				if (articles.size() == 0) {
					System.out.println("게시글이 없습니다");
					continue;
				}
				System.out.println("번호    |    제목");
				for (int i = articles.size() - 1; i >= 0; i--) {
					Article article = articles.get(i);
					System.out.printf("%4d    |    %s\n", article.id, article.title);
				}
			} else if (cmd.startsWith("article detail ")) {
				String[] cmdBits = cmd.split(" ");

				int id = Integer.parseInt(cmdBits[2]); // "2" -> 2

				Article foundArticle = null;

				for (int i = 0; i < articles.size(); i++) { // 0~4
					Article article = articles.get(i);

					if (article.id == id) {
						foundArticle = article;
						break;
					}
				}

				if (foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
					continue;
				}
				System.out.printf("번호 : %d\n", foundArticle.id);
				System.out.printf("날짜 : %s\n", foundArticle.regDate);
				System.out.printf("제목 : %s\n", foundArticle.title);
				System.out.printf("내용 : %s\n", foundArticle.body);
			} else {
				System.out.println("존재하지 않는 명령어입니다");
			}

		}

		sc.close();

		System.out.println("==프로그램 끝==");
	}
}

class Article {
	int id;
	String regDate;
	String title;
	String body;

	Article(int id, String regDate, String title, String body) {
		this.id = id;
		this.regDate = regDate;
		this.title = title;
		this.body = body;
	}
}