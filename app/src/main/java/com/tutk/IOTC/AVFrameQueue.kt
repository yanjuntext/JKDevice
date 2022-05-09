package com.tutk.IOTC

import java.util.concurrent.locks.ReentrantLock

/**
 * @Author: wangyj
 * @CreateDate: 2021/9/8
 * @Description:
 */
class AVFrameQueue {
    private val listData = mutableListOf<AVFrame>()

    @Volatile
    var mSize = 0

    @Volatile
    var isDroping = false

    private val lock = ReentrantLock()

    private val addLock = ReentrantLock()

    fun removeHead(): AVFrame? {

        val iterator = listData.iterator()
        return if(iterator.hasNext()){
            val avFrame = iterator.next()
            iterator.remove()

            mSize--
            avFrame
        }else null

//        lock.lock()
//        return if (mSize == 0) {
//            lock.unlock()
//            null
//        } else {
//
//            val avFrame = listData.removeAt(0)
//            mSize--
//            lock.unlock()
//            avFrame
//        }
    }

    fun addLast(avFrame: AVFrame) {

//        addLock.lock()
        if(listData.size > 1500){
            removeHead()
        }
//        if (mSize > 1500) {
//            removeHead()
//        }
        listData.add(avFrame)
        mSize++
//        addLock.unlock()
    }

    fun removeAll() {

        val iterator = listData.iterator()
        while (iterator.hasNext()){
            iterator.next()
            iterator.remove()
        }
        mSize = 0

//        addLock.lock()
//        lock.lock()
//        listData.clear()
//        mSize = 0
//        lock.unlock()
//        addLock.unlock()
    }

    fun isFirstIFrame(): Boolean {
        val iterator = listData.iterator()
        return if(iterator.hasNext()){
            iterator.next().isIFrame()
        } else false


//        var value = false
//        lock.lock()
//        if (listData.isNotEmpty()) {
//            value = listData[0].isIFrame()
//        }
//        lock.unlock()
//        return value
    }
}