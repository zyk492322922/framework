import request from '@/utils/request'

// 查询广告banner信息列表
export function listBanner(query) {
  return request({
    url: '/admin/banner/list',
    method: 'get',
    params: query
  })
}

// 查询广告banner信息详细
export function getBanner(id) {
  return request({
    url: '/admin/banner/' + id,
    method: 'get'
  })
}

// 新增广告banner信息
export function addBanner(data) {
  return request({
    url: '/admin/banner',
    method: 'post',
    data: data
  })
}

// 修改广告banner信息
export function updateBanner(data) {
  return request({
    url: '/admin/banner',
    method: 'put',
    data: data
  })
}

// 删除广告banner信息
export function delBanner(id) {
  return request({
    url: '/admin/banner/' + id,
    method: 'delete'
  })
}

// 导出广告banner信息
export function exportBanner(query) {
  return request({
    url: '/admin/banner/export',
    method: 'get',
    params: query
  })
}