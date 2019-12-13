import Router from "koa-router";
const router = new Router();
import { post } from "../utils/axios"
import async  from "async";
/**
 *  页面路由
 * 
 *  */ 

router.get('/', async (ctx, next) => {
  const params = {}
  post("/index/select",params)
  //const res = await post("/index/select",params)
  await ctx.render('index', {
    title: '首页'
  })


    // async.parallel({
    //     activityList: function (callback) {
    //         post('/index/select', {}, function (c_err, c_res, c_body) {
    //                 if(c_body&&c_body.Msg){
    //                     callback(c_err, c_body.Info);
    //                 }else{
    //                     c_body.Info="";
    //                     callback(c_err, c_body.Info);
    //                 }
    //             });
    //     }
    // }, function (err, result) {
    //     result.title = "新闻资讯";
    //     ctx.render('index', result);
    // });
})

/**
 *  个人中心页面
 *
 *  */
router.get('/user', async (ctx, next) => {
    console.log(ctx.sub)
    try{
        await ctx.render('users', {
            title: '个人中心',
            content: ctx.sub
        })
    }catch(e){
        ctx.render("error",{
            message: e,
            status: "500",
            error:e
        })
    }

})


router.get('/activity', async (ctx, next) => {
  await ctx.render('activity', {
    title: '热门活动'
  })
})

router.get('/activity-gone', async (ctx, next) => {
  await ctx.render('activity', {
    title: '往期活动'
  })
})

router.get('/activity/:id', async (ctx, next) => {
  await ctx.render('activityDetail', {
    title: '活动详情'
  })
})


router.get('*', async (ctx, next) => {
  await ctx.render('error', {
    title: '404'
  })
})


module.exports = router
