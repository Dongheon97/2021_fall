package listener.main;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import generated.MiniCParser;
import generated.MiniCParser.Fun_declContext;
import generated.MiniCParser.Local_declContext;
import generated.MiniCParser.ParamsContext;
import generated.MiniCParser.Type_specContext;
import generated.MiniCParser.Var_declContext;
import listener.main.SymbolTable.Type;
import static listener.main.BytecodeGenListenerHelper.*;


public class SymbolTable {
	enum Type {
		INT, INTARRAY, VOID, ERROR
	}
	
	static public class VarInfo {
		Type type; 
		int id;
		int initVal;
		
		public VarInfo(Type type,  int id, int initVal) {
			this.type = type;
			this.id = id;
			this.initVal = initVal;
		}
		public VarInfo(Type type,  int id) {
			this.type = type;
			this.id = id;
			this.initVal = 0;
		}
	}

	static public class FInfo {
		public String sigStr;
	}
	
	private Map<String, VarInfo> _lsymtable = new HashMap<>();	// local v.
	private Map<String, VarInfo> _gsymtable = new HashMap<>();	// global v.
	private Map<String, FInfo> _fsymtable = new HashMap<>();	// function 
	
		
	private int _globalVarID = 0;
	private int _localVarID = 0;
	private int _labelID = 0;
	private int _tempVarID = 0;
	
	SymbolTable(){
		initFunDecl();
		initFunTable();
	}
	
	void initFunDecl(){		// at each func decl
		_localVarID = 0;
		_labelID = 0;
		_tempVarID = 32;		
	}
	
	void putLocalVar(String varname, Type type){
		//<Fill here>
		_lsymtable.put(varname, new VarInfo(type, _localVarID++));
	}
	void putLocalVarWithInitVal(String varname, Type type, int initVar){
		//<Fill here>
		_lsymtable.put(varname, new VarInfo(type, _localVarID++, initVar));
	}

	void putGlobalVar(String varname, Type type){
		//<Fill here>
		_gsymtable.put(varname, new VarInfo(type, _globalVarID++));
	}

	void putGlobalVarWithInitVal(String varname, Type type, int initVar){
		//<Fill here>
		_gsymtable.put(varname, new VarInfo(type, _globalVarID++, initVar));
	}
	
	void putParams(ParamsContext params) {
		for(int i = 0; i < params.param().size(); i++) {
		//<Fill here>
			if(params.param().get(i).getChild(0).getText().equals("int")){
				// int -> Type.INT
				_lsymtable.put(params.param().get(i).getChild(1).getText(), new VarInfo(Type.INT, _localVarID++));
			}
			else{
				// else -> Type == null
				_lsymtable.put(params.param().get(i).getChild(1).getText(), new VarInfo(null, _localVarID++));
			}
		}
	}
	
	private void initFunTable() {
		FInfo printlninfo = new FInfo();
		printlninfo.sigStr = "java/io/PrintStream/println(I)V";
		
		FInfo maininfo = new FInfo();
		maininfo.sigStr = "main([Ljava/lang/String;)V";
		_fsymtable.put("_print", printlninfo);
		_fsymtable.put("main", maininfo);
	}
	
	public String getFunSpecStr(String fname) {		
		// <Fill here>
		if(_fsymtable.get(fname) == null){
			// search by fname -> FunSpec is null -> return nothing
			return "";
		}
		else{
			// search by fname -> FunSpec is not null -> return FInfo's sigStr
			return _fsymtable.get(fname).sigStr;
		}
	}

	public String getFunSpecStr(Fun_declContext ctx) {
		// <Fill here>
		// given ctx -> return FInfo
		return _fsymtable.get(ctx.IDENT().getText()).sigStr;
	}
	
	public String putFunSpecStr(Fun_declContext ctx) {
		String fname = getFunName(ctx);
		String argtype = "";	
		String rtype = "";
		String res = "";
		
		// <Fill here>
		// fun_decl	: type_spec IDENT '(' params ')' compound_stmt ;
		// function's parameters type
		String[] params = ctx.getChild(3).getText().split(",");

		for(int i=0; i<params.length; i++){
			if(params[i].startsWith("int")){
				// fun(I)
				argtype += "I";
			}
			else if(params[i].startsWith("void")){
				// fun(V)
				argtype += "V";
			}
		}

		// function's return type
		if(ctx.getChild(0).getText().equals("int")){
			// fun()I
			rtype += "I";
		}
		else if(ctx.getChild(0).getText().equals("void")){
			// fun()V
			rtype += "V";
		}
		
		res += fname + "(" + argtype + ")" + rtype;
		
		FInfo finfo = new FInfo();
		finfo.sigStr = res;
		_fsymtable.put(fname, finfo);
		
		return res;
	}
	
	String getVarId(String name){
		// <Fill here>
		// Only consider local variable ID
		VarInfo lvar = (VarInfo) _lsymtable.get(name);
		return Integer.toString(lvar.id);
	}
	
	Type getVarType(String name){
		VarInfo lvar = (VarInfo) _lsymtable.get(name);
		if (lvar != null) {
			return lvar.type;
		}
		
		VarInfo gvar = (VarInfo) _gsymtable.get(name);
		if (gvar != null) {
			return gvar.type;
		}
		
		return Type.ERROR;	
	}
	String newLabel() {
		return "label" + _labelID++;
	}
	
	String newTempVar() {
		String id = "";
		return id + _tempVarID--;
	}

	// global
	public String getVarId(Var_declContext ctx) {
		// <Fill here>
		return getVarId(ctx.IDENT().getText());
	}

	// local
	public String getVarId(Local_declContext ctx) {
		String sname = "";
		sname += getVarId(ctx.IDENT().getText());
		return sname;
	}
}
