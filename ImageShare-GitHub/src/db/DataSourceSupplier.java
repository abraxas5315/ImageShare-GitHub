package db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


/**
 * データソース供給クラス
 *
 * @author t.yoshida
 *
 */
public class DataSourceSupplier
{
	// 当クラスのインスタンス
	private static final DataSourceSupplier _supplier;

	// データソース
	private DataSource _dataSource;


	static
	{
		_supplier = new DataSourceSupplier();
	}

	private DataSourceSupplier()
	{
		_dataSource = createDataSource();
	}

	/**
	 * インスタンスを返す。
	 *
	 * @return
	 */
	public static final DataSourceSupplier getInstance()
	{
		return _supplier;
	}

	/**
	 * コネクションを返す。
	 * <pre>
	 * ※コネクション使用後は必ずクローズすること
	 * </pre>
	 *
	 * @return コネクション
	 * @throws SQLException
	 */
	Connection getConnection() throws SQLException
	{
		if(_dataSource == null)
		{
			// データソース取得できなかった場合
			throw new SQLException();
		}

		return _dataSource.getConnection();
	}

	/*
	 * データソースの作成
	 */
	private DataSource createDataSource()
	{
		InitialContext ic = null;
		DataSource ds = null;
		try
		{
			ic = new InitialContext();
			ds = (DataSource)ic.lookup("java:comp/env/MySQL_JDBC");
		}
		catch(NamingException ex)
		{
			if(ic != null)
			{
				try
				{
					ic.close();
				}
				catch(NamingException ex2) { }
			}

			ex.printStackTrace(System.err);
		}

		return ds;
	}
}
