import request from '@/utils/request'

// 查询平台用户列表
export function listUser(query) {
  return request({
    url: '/admin/user/list',
    method: 'get',
    params: query
  })
}

// 查询平台用户详细
export function getUser(userId) {
  return request({
    url: '/admin/user/' + userId,
    method: 'get'
  })
}

// 新增平台用户
export function addUser(data) {
  return request({
    url: '/admin/user',
    method: 'post',
    data: data
  })
}

// 修改平台用户
export function updateUser(data) {
  return request({
    url: '/admin/user',
    method: 'put',
    data: data
  })
}

// 删除平台用户
export function delUser(userId) {
  return request({
    url: '/admin/user/' + userId,
    method: 'delete'
  })
}

// 导出平台用户
export function exportUser(query) {
  return request({
    url: '/admin/user/export',
    method: 'get',
    params: query
  })
}