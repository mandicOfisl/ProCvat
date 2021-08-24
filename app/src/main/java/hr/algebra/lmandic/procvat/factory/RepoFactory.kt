package hr.algebra.lmandic.procvat.factory

import android.content.Context
import hr.algebra.lmandic.procvat.dao.ProcvatSqlHelper

fun getProcvatRepo(context: Context?) = ProcvatSqlHelper(context)