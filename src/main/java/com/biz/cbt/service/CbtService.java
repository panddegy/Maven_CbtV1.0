package com.biz.cbt.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.biz.cbt.vo.CbtVO;
import com.biz.cbt.vo.ResultVO;

public class CbtService {

	Scanner sc;
	DBService dbService;
	
	public CbtService() {
		//TODO CbtService
		sc=new Scanner(System.in);
		dbService=new DBService();
	}
	
	public void cbtStart() {
		//TODO cbtStart
		while(true) {
			System.out.println("=============================================");
			System.out.println("       정보처리 기사 실기문제 CBT v1.0");
			System.out.println("=============================================");
			System.out.println(">> 1.풀기   2.관리   3.보기   0.CBT종료");
			System.out.println("---------------------------------------------");
			int intMenu=checkMenu();
			if(intMenu==0) {
				System.out.println(">> CBT를 종료합니다.");
				break;
			}
			if(intMenu==1) startQuestion();
			if(intMenu==2) managementCbt();
			if(intMenu==3) selectQuestion();
			
			
		}
	}
	
	public void managementCbt() {
		// TODO managementCbt
		while(true) {
			System.out.println("=============================================");
			System.out.println("       정보처리 기사 실기문제 CBT v1.0");
			System.out.println("=============================================");
			System.out.println(">> 1.입력   2.수정   3.삭제   0.돌아가기");
			System.out.println("---------------------------------------------");
			int intMenu=checkMenu();
			if(intMenu==0) break;
			if(intMenu==1) insertCbt();
			if(intMenu==2) updateQuestion();
			if(intMenu==3) deleteQuestion();
			
		}
	}
	
	public void selectQuestion() {
		//TODO selectQuestion
		List<CbtVO> cbtList=dbService.selectAllCbt();
		System.out.println("---------------------------------------------");
		for(CbtVO v:cbtList) {
			viewCbtVO(v);
		}
		System.out.println("---------------------------------------------");
	}
	
	public void insertCbt() {
		//TODO insertCbt
		CbtVO vo=insertQuestion();
		if(vo==null) return;
		System.out.println();
		System.out.println(">> 전송 하시겠습니까?(y/n)");
		System.out.print(">> ");
		String strYN=sc.nextLine();
		if(strYN.equalsIgnoreCase("y")) {
			if(dbService.insertCbt(vo)>0) {
				System.out.println(">> 전송이 완료되었습니다.");
			} else {
				System.out.println(">> 전송이 실패하였습니다.");
			}
		} else {
			System.out.println(">> 전송이 취소되었습니다.");
		}
	}
	
	public void updateQuestion() {
		//TODO updateQuestion
		System.out.println(">> 수정할 문제의 ID를 입력하세요.");
		System.out.print(">> ");
		String strID=sc.nextLine();
		System.out.println();
		try {
			long longID=Long.valueOf(strID);
			CbtVO v=dbService.findByIDCbt(longID);
			viewCbtVO(v);
			CbtVO vo=insertQuestion();
			if(vo==null) return;
			vo.setCb_id(v.getCb_id());
			System.out.println();
			System.out.println(">> 전송 하시겠습니까?(y/n)");
			System.out.print(">> ");
			String strYN=sc.nextLine();
			if(strYN.equalsIgnoreCase("y")) {
				if(dbService.updateCbt(vo)>0) {
					System.out.println(">> 전송이 완료되었습니다.");
				} else {
					System.out.println(">> 전송이 실패하였습니다.");
				}
			} else {
				System.out.println(">> 전송이 취소되었습니다.");
			}
		} catch (NumberFormatException e) {
			System.out.println(">> 입력이 잘못되었습니다.");
			return;
		}
	}

	public CbtVO insertQuestion() {
		
		System.out.println(">> 문제 지문을 입력하세요.");
		System.out.print(">> ");
		String strQuestion=sc.nextLine();
		String strExample="";
		for(int i=0; i<4; i++) {
			System.out.println(">> "+(i+1)+"번 보기를 입력하세요.");
			System.out.print(">> ");
			strExample+=sc.nextLine()+"/";
		}
		System.out.println(">> 정답의 번호를 입력하세요.");
		System.out.print(">> ");
		String strAnswer=sc.nextLine();
		try {
			String[] strExamples=strExample.split("/");
			strAnswer=strExamples[(Integer.valueOf(strAnswer))-1];
			CbtVO vo=new CbtVO(strQuestion, strExample, strAnswer);
			return vo;
		} catch (Exception e) {
			System.out.println(">> 입력이 잘못되었습니다.");
			return null;			
		}
	}

	public void deleteQuestion() {
		//TODO deleteQuestion
		System.out.println(">> 삭제할 문제의 ID를 입력하세요.");
		System.out.print(">> ");
		String strID=sc.nextLine();
		System.out.println();
		try {
			long longID=Long.valueOf(strID);
			CbtVO vo=dbService.findByIDCbt(longID);
			viewCbtVO(vo);
			System.out.println(">> 해당문제를 삭제하시겠습니까?(y/n)");
			System.out.print(">> ");
			String strYN=sc.nextLine();
			if(strYN.equalsIgnoreCase("y")) {
				if(dbService.deleteCbt(longID)>0) {
					System.out.println(">> 삭제가 완료되었습니다.");
				} else {
					System.out.println(">> 삭제가 실패하였습니다.");
				}
			} else {
				System.out.println(">> 삭제가 취소되었습니다.");
			}
		} catch (NumberFormatException e) {
			System.out.println(">> 입력이 잘못되었습니다.");
			return;
		}
		
	}

	public void startQuestion() {
		//TODO startQuestion
		System.out.println(">> ID를 입력하세요");
		System.out.print(">> ");
		String re_user=sc.nextLine();
		ResultVO vo=checkID(re_user);
		List<CbtVO> cbtList=makeQuestion(vo);
		System.out.println(">> CBT를 시작합니다.");
		System.out.println();
		Collections.shuffle(cbtList);
		String q_id="";
		String sheet="";
		String t_sheet="";
		int viewNum=0;
		int totalScore=0;
		int questionIndex=1;
		int size=20;
		if(size>cbtList.size()) size=cbtList.size();
		for(int i=0; i<size; i++) {
			int answerIndex=1;
			System.out.print((questionIndex)+". ");
			lineChange(cbtList.get(i));
			System.out.println();
			String[] examples=cbtList.get(i).getCb_example().split("/");
			Collections.shuffle(Arrays.asList(examples));
			for(String s:examples) {
				System.out.println("  "+(answerIndex++)+"> "+s);
			}
			int retAnswer=answer(examples,cbtList.get(i).getCb_answer());
			if(retAnswer<0) break;
			if(retAnswer==1) {
				t_sheet+="1";
				sheet+="1";
				totalScore+=5;
			} else {
				t_sheet+="0";
				sheet+="0";
			}
			q_id+=""+cbtList.get(i).getCb_id()+":";
			System.out.println();
			if(questionIndex%5==0) {
				viewMidScore(t_sheet, viewNum);
				viewNum+=5;
				t_sheet="";
				System.out.println("점수>> "+totalScore);
				System.out.println();
			}
			questionIndex++;
		}
		if(sheet=="") return;
		viewResult(sheet);
		vo.setRe_qid(q_id);
		vo.setRe_answer(sheet);
		dbService.insertResult(vo);
	}
	
	public int answer(String[] examples, String cb_answer) {
		//TODO answer
		int i=0;
		while(true) {
			System.out.println();
			System.out.print("정답(-1:종료)>> ");
			String answer=sc.nextLine();
			if(answer.equals("")) continue;
			int intAnswer=Integer.valueOf(answer);
			if(intAnswer<0) return -1;
			if(examples[intAnswer-1].equals(cb_answer)) {
				System.out.println();
				System.out.println(">> 정답입니다.");
				return 1;
			} else {
				System.out.println();
				System.out.println(">> 오답입니다. ");
				if(i==1) return 0;
				System.out.println(">> 다시 풀기 : 1  넘어가기 : Enter ");
				System.out.print(">> ");
				String strRe=sc.nextLine();
				if(strRe.equals("1")) i=1;
				else return 0;
			}
		}
	}
	
	public void lineChange(CbtVO vo) {
		//TODO lineChange
		String[] questions=vo.getCb_question().split("");
		for(int i=0; i<questions.length; i++) {
			System.out.print(questions[i]);
			if((i!=0)&&(i%25==0)) {
				System.out.println();
				System.out.print("   ");
			}
		}
		System.out.println();
	}

	public void viewMidScore(String t_sheet, int viewNum) {
		// TODO viewMidScore
		String[] t_sheets=t_sheet.split("");
		System.out.print("문항>> ");
		for(int i=0; i<5; i++) {
			System.out.print((i+1+viewNum)+" ");
		}
		System.out.println();
		System.out.print("답안>> ");
		for(String s:t_sheets) {
			if(s.equals("1")) System.out.print("O ");
			else System.out.print("X ");
		}
		System.out.println();
	}
	
	public void viewResult(String sheet) {
		//TODO viewResult
		int totalScore=0;
		String[] sheets=sheet.split("");
		System.out.print("문항>> ");
		for(int i=0; i<sheets.length; i++) {
			System.out.print((i+1)+" ");
		}
		System.out.println();
		System.out.print("답안>> ");
		for(String s:sheets) {
			if(s.equals("1")) {
				System.out.print("O ");
				totalScore+=5;
			} else System.out.print("X ");
		}
		System.out.println();
		System.out.println("점수>> "+totalScore);
		System.out.println();
	}

	public ResultVO checkID(String re_user) {
		//TODO checkID
		ResultVO vo=dbService.findByUserResult(re_user);
		if(dbService.findByUserResult(re_user)==null) {
			return vo=new ResultVO(re_user);
		} else {
			System.out.println(">> ID가 존재합니다. 틀린 문제만 풀겠습니끼(y/n)? ");
			System.out.print(">> ");
			String strYN=sc.nextLine();
			if(strYN.equalsIgnoreCase("y")) return vo;
			else return vo=new ResultVO(re_user); 
		}
	}
	
	public List<CbtVO> makeQuestion(ResultVO vo) {
		//TODO makeQuestion
		if(vo.getRe_qid()==null) {
			List<CbtVO> cbtList=dbService.selectAllCbt();
			return cbtList;
		} else {
			List<CbtVO> cbtList=checkQuestion(vo);
			return cbtList;
		}
	}
	
	public List<CbtVO> checkQuestion(ResultVO vo){
		//TODO checkQuestion
		List<CbtVO> cbtList=dbService.selectAllCbt();
		String[] re_qid=vo.getRe_qid().split(":");
		String[] re_answer=vo.getRe_answer().split("");
		int i=0;
		int size=cbtList.size();
		while(true) {
			String cb_id=String.valueOf(cbtList.get(i).getCb_id());
			for(int j=0; j<re_qid.length; j++) {
				if(cb_id.equals(re_qid[j])&&re_answer[j].equals("1")) {
					cbtList.remove(i);
					size--;
					i--;
					break;
				}
			}
			i++;
			if(i==size) break;
		}
		return cbtList;
	}
	
	public int checkMenu() {
		//TODO checkMenu
		while(true) {
			System.out.print(">> ");
			String strMenu=sc.nextLine();
			try {
				int intMenu=Integer.valueOf(strMenu);
				if(intMenu>3 || intMenu<0) {
					System.out.println(">> 입력이 잘못되었습니다.");
					continue;
				}
				return intMenu;
			} catch (NumberFormatException e) {
				System.out.println(">> 입력이 잘못되었습니다.");
				continue;
			}
		}
	}
	
	public void viewCbtVO(CbtVO vo) {
		//TODO viewCbtVO
		if(vo==null) {
			System.out.println(">> 해당하는 문제가 없습니다.");
			return;
		}
		String[] examples=vo.getCb_example().split("/");
		System.out.println(">> ID   : "+vo.getCb_id());
		System.out.print(">> 문제 : ");
		lineChange(vo);
		for(String s:examples) {
			System.out.println(">> 보기 : "+s);
		}
		System.out.println();
		System.out.println(">> 정답 : "+vo.getCb_answer());
		System.out.println();
	}
	
	public void insertBulk() {
		//TODO insertBulk
		String file="src/main/java/com/biz/cbt/question.txt";
		
		FileReader fr;
		BufferedReader buffer;
		
		List<CbtVO> insertList=new ArrayList();
		
		try {
			fr=new FileReader(file);
			buffer=new BufferedReader(fr);
			while(true) {
				String reader=buffer.readLine();
				if(reader==null) break;
				String[] cbts=reader.split(":");
				CbtVO vo=new CbtVO();
				vo.setCb_question(cbts[0]);
				vo.setCb_example(cbts[1]);
				vo.setCb_answer(cbts[2]);
				insertList.add(vo);
			}
			buffer.close();
			fr.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(CbtVO v:insertList) {
			dbService.insertCbt(v);
		}
	}
}

