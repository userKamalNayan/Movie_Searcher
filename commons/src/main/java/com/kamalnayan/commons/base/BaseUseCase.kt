package com.kamalnayan.commons.base

/** @Author Kamal Nayan
Created on: 25/01/24
 **/
abstract class BaseUseCase<in Params, out Result> {

    abstract suspend operator fun invoke(params: Params?=null): Result
}