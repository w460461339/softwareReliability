package example;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.antlr.v4.runtime.Token;

import parser.SimpleCBaseVisitor;
import parser.SimpleCParser.AddExprContext;
import parser.SimpleCParser.AssertStmtContext;
import parser.SimpleCParser.AssignStmtContext;
import parser.SimpleCParser.ExprContext;
import parser.SimpleCParser.MulExprContext;
import parser.SimpleCParser.ParenExprContext;
import parser.SimpleCParser.UnaryExprContext;

public class Count42sVisitor2 extends SimpleCBaseVisitor<Void> {

	private int num42s = 0;
	private boolean inAssert = false;
	// private List<String> opraList=new ArrayList<String>();
	// private List<String> argList=new ArrayList<String>();
	private Queue<String> oprQue = new LinkedList<String>();
	private Queue<String> argQue = new LinkedList<String>();
	private BTree root=new BTree();
	private Queue<BTree> btList=new LinkedList<BTree>();

	// Assert语句
	@Override
	public Void visitAssertStmt(AssertStmtContext ctx) {
		inAssert = true;
		super.visitAssertStmt(ctx);
		inAssert = false;
		return null;
	}

	// 表达式语句
	@Override
	public Void visitExpr(ExprContext ctx) {
		return super.visitExpr(ctx);
	}

	// 赋值语句
	@Override
	public Void visitAssignStmt(AssignStmtContext ctx) {
		for(int i=0;i<ctx.getChildCount();i++){
			System.out.println("ASS:  "+ctx.getChild(i).getText());
		}
		super.visitAssignStmt(ctx);
		System.out.println("BT SIZE:"+btList.size());
//		BTree test=btList.poll();
//		System.out.println(test.getValue());
//		System.out.println(test.getLeft().getValue());
//		this.frontFirstFind(test);
		return null;
	}
	
	// 加法语句
	@Override
	public Void visitAddExpr(AddExprContext ctx) {
		List<String> opsList=new ArrayList<String>();
		List<String> argList=new ArrayList<String>();
		for(Token token:ctx.ops){
			opsList.add(token.getText());
			System.out.println("add:"+token.getText());
		}
		for(MulExprContext mec:ctx.args){
			argList.add(mec.getText());
			System.out.println("add:"+mec.getText());
		}
		return super.visitAddExpr(ctx);
	}
	
	// 乘除法
	@Override
	public Void visitMulExpr(MulExprContext ctx) {

		return super.visitMulExpr(ctx);
	}
	
	// 一元操作
	@Override
	public Void visitUnaryExpr(UnaryExprContext ctx) {
		 super.visitUnaryExpr(ctx);
		// 表示这个Unary表达式以- + ~ ! 开头
		if(ctx.arg!=null){
			String arg=ctx.arg.getText();
			// 如果这个一元表达式不包含括号，执行下列语句
			// 不然就什么也不管
			if(!arg.contains("(")){
				System.out.println(arg);
				// 创建只包涵内容的节点
				BTree argBT=new BTree(arg);
				BTree opsBT=null;
				for(Token token:ctx.ops){
					// 如果根节点是空，创建根节点
					if(opsBT==null){
						opsBT=new BTree(token.getText());
					}else{
						// 如果根节点不为空，为新的符号创建新的节点
						BTree temp=new BTree(token.getText());
						// leftNode指向opsBT的根节点
						BTree leftNode=opsBT;
						// 找到leftNode的左节点为空的节点
						while(leftNode.getLeft()!=null){
							leftNode=leftNode.getLeft();
						}
						// 将新的节点放到左节点上
						leftNode.setLeft(temp);
					}
				}
				BTree leftNode=opsBT;
				// 找到leftNode的左节点为空的节点
				while(leftNode.getLeft()!=null){
					leftNode=leftNode.getLeft();
				}
				// 将值放到最左节点上
				leftNode.setLeft(argBT);
				// 把根节点加到list里面
				this.btList.add(opsBT);
			}
		}
		
		for(int i=0;i<ctx.getChildCount();i++){
			System.out.println(ctx.getChild(i).getText());
		}
		return null;
	}

	/**
	 * 括号
	 */
	@Override
	public Void visitParenExpr(ParenExprContext ctx) {
		System.out.println("lol");
		return super.visitParenExpr(ctx);
	}
	
	// 先序遍历
	private Void frontFirstFind(BTree root){
		// 如果内容不为空
		if(root.getValue()!=null){
			System.out.println(root.getValue());
			if(root.getLeft()!=null){
				frontFirstFind(root.getLeft());
			}
			if(root.getRight()!=null){
				frontFirstFind(root.getRight());
			}
		}
	
		return null;
	}
	
}	

class BTree{
	private String value=null;
	private BTree left=null;
	private BTree right=null;
	public BTree() {
		// TODO Auto-generated constructor stub
	}
	
	public BTree(String value){
		this.value=value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public BTree getLeft() {
		return left;
	}

	public void setLeft(BTree left) {
		this.left = left;
	}

	public BTree getRight() {
		return right;
	}

	public void setRight(BTree right) {
		this.right = right;
	}
}